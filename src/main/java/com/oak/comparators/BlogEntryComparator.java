package com.oak.comparators;

import java.util.Comparator;

import com.oak.entities.BlogPost;

public class BlogEntryComparator implements Comparator<BlogPost> {

	public int compare(BlogPost o1, BlogPost o2) {
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
