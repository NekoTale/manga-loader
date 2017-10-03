package com.dimon.bashorg.core;

import com.dimon.bashorg.MangaChapters;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class mangaPageParseImpl implements  PageParse{
    public MyLinkedList<MangaChapters> selectedMangaTitles (final String mangaPage){
        Document doc = Jsoup.parse(mangaPage);  //парсим страницу с мангой
        Elements chapterList = doc.select("div.expandable.chapters-link a");
        MyLinkedList<MangaChapters> mangaCharpetsData = new MyLinkedList<MangaChapters>();
        for (Element element : chapterList){  // пролистываем все названия по очереди
            MangaChapters selectedManagaChaptersList = new MangaChapters();
            mangaCharpetsData.add(selectedManagaChaptersList);
            String titleUrl = element.select("a").attr("href");
            String title = element.select("a").text();
            selectedManagaChaptersList.setChapterName(title);
            selectedManagaChaptersList.setChapterUrl("http://readmanga.me" + titleUrl);
        }
        mangaCharpetsData.reverse();
        int num = 0;
        for (int i = 0; i<=mangaCharpetsData.length(); i++){
            mangaCharpetsData.get(i).setChapterNumber(num);
            num++;
        }

        return mangaCharpetsData;
    }


}
