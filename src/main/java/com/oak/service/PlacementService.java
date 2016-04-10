package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Placement;
import com.oak.entities.PlacementKey;
import com.oak.repositories.PlacementRepo;

@Service("placementService")
public class PlacementService {

	@Autowired
	PlacementRepo placementRepo;

	public List<Placement> getPlacements() {

		return placementRepo.getPlacements();

	}

	public List<Placement> findBySection(String section) {
		return placementRepo.getPlacementsBySection(section);
	}

	public Placement getPlacementById(String id) {

		PlacementKey pk = new PlacementKey();

		String[] keyarr = id.split("_");

		String page = keyarr[0];
		String section = keyarr[1];
		int pos = Integer.parseInt(keyarr[2]);

		pk.setPage(page);
		pk.setSection(section);
		pk.setPosition(pos);

		return placementRepo.getPlacementById(pk);
	}

	public void deletePlacementById(String id) {

		PlacementKey pk = new PlacementKey();

		String[] keyarr = id.split("_");

		String page = keyarr[0];
		String section = keyarr[1];
		int pos = Integer.parseInt(keyarr[2]);

		pk.setPage(page);
		pk.setSection(section);
		pk.setPosition(pos);

		placementRepo.deletePlacementById(pk);
	}

	@Transactional
	public void createPlacement(Placement placement) {

		placementRepo.createPlacement(placement);

	}

	@Transactional
	public void updatePlacement(Placement placement) {

		placementRepo.updatePlacement(placement);
	}

}
