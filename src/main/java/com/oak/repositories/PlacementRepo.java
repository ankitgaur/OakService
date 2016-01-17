package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Placement;
import com.oak.utils.OakCassandraTemplate;

@Repository("placementRepo")
@Transactional
public class PlacementRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<Placement> getPlacements() {
		List<Placement> placements = oakCassendraTemplate.findAll(Placement.class);
		return placements;
	}
	
	public Placement getPlacementById(String id) {
		Placement placement = oakCassendraTemplate.findById(id, Placement.class);
		return placement;
	}

	public void deletePlacementById(String id) {

		oakCassendraTemplate.deleteById(id, Placement.class);

	}

	public void createPlacement(Placement placement) {

		oakCassendraTemplate.create(placement, Placement.class);

	}

	public void updatePlacement(Placement placement) {

		oakCassendraTemplate.update(placement, Placement.class);

	}

}
