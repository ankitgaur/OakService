package com.oak.comparators;

import java.util.Comparator;

import com.oak.entities.ForumPost;

public class ForumPostComparator implements Comparator<ForumPost> {

	public int compare(ForumPost o1, ForumPost o2) {
		// TODO Auto-generated method stub

		if (o1.getHits() == null) {
			o1.setHits(0L);
		}
		if (o2.getHits() == null) {
			o2.setHits(0L);
		}

		long diff = o2.getHits() - o1.getHits();
		return (int) diff;
	}

}
