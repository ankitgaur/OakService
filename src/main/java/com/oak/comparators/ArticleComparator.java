package com.oak.comparators;

import java.util.Comparator;

import com.oak.entities.Article;

public class ArticleComparator implements Comparator<Article> {

	public int compare(Article o1, Article o2) {
		// TODO Auto-generated method stub
		
		if(o1.getHits()==null){
			o1.setHits(0L);
		}
		if(o2.getHits()==null){
			o2.setHits(0L);
		}
		
		long diff = o2.getHits() - o1.getHits();
		return (int)diff;
	}

}
