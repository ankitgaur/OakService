package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Section;
import com.oak.entities.SectionKey;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("sectionsRepo")
public class SectionsRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Section> getSections() {
		List<Section> sections = oakCassandraTemplate.findAll(Section.class);
		return sections;
	}

	public Section getSectionsById(SectionKey id) {
		Section section = oakCassandraTemplate.findById(id, Section.class);
		return section;
	}

	public void createSection(Section section) {

		oakCassandraTemplate.create(section, Section.class);

	}

	public void updateSection(Section user) {

		oakCassandraTemplate.update(user, Section.class);

	}

	public void deleteSectionById(SectionKey id) {

		oakCassandraTemplate.deleteById(id, Section.class);

	}

}
