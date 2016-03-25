package com.oak.comparators;

import java.util.Comparator;

import com.oak.entities.BlogEntry;

public class BlogEntryComparator implements Comparator<BlogEntry> {

	public int compare(BlogEntry o1, BlogEntry o2) {
		// TODO Auto-generated method stub
		long diff = o2.getHits() - o1.getHits();
		return (int)diff;
	}

}
