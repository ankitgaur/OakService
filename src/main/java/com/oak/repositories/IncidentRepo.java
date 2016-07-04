package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Incident;
import com.oak.entities.IncidentKey;
import com.oak.utils.IncidentConstants;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("incidentRepo")
public class IncidentRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	@Autowired
	private CountersRepo countersRepo;

	public List<Incident> getIncidents() {
		List<Incident> incidents = oakCassendraTemplate.findAll(Incident.class);
		return incidents;
	}

	public List<Incident> getTopIncidentsByIncidentsTypes(String incidenttype,
			int limit) {
		String incidents_by_incidenttype_qry = "SELECT * FROM incidents WHERE incidentstype=";
		incidents_by_incidenttype_qry = incidents_by_incidenttype_qry + "'"
				+ incidenttype + "'" + " LIMIT " + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ incidents_by_incidenttype_qry);
		List<Incident> incidents = oakCassendraTemplate.findByPartitionKey(
				incidents_by_incidenttype_qry, Incident.class);
		return incidents;
	}

	public List<Incident> getTopIncidentsByLimit(int limit) {
		String incidents_by_limit_qry = "SELECT * FROM incidents LIMIT ";
		incidents_by_limit_qry = incidents_by_limit_qry + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ incidents_by_limit_qry);
		List<Incident> articles = oakCassendraTemplate.findByLimit(
				incidents_by_limit_qry, Incident.class);
		return articles;
	}
	
	public List<Incident> getIncidentsAfterId(long createdOn, int limit) {
		String incidents_by_limit_qry = "SELECT * FROM incidents where createdon < " + createdOn +" LIMIT ";
		incidents_by_limit_qry = incidents_by_limit_qry + limit + " ALLOW FILTERING";
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ incidents_by_limit_qry);
		List<Incident> articles = oakCassendraTemplate.findByLimit(
				incidents_by_limit_qry, Incident.class);
		return articles;
	}

	public Incident getIncidentById(IncidentKey id) {
		Incident incident = oakCassendraTemplate.findById(id, Incident.class);
		return incident;
	}

	public void createIncident(Incident incident) {
		oakCassendraTemplate.create(incident, Incident.class);

		countersRepo.increment(IncidentConstants.STATE_INCIDENT_TYPE_PREFIX
				+ "_" + incident.getState() + "_" + incident.getType());
		countersRepo.increment(IncidentConstants.GOVT_INCIDENT_TYPE_PREFIX
				+ "_" + incident.getGovt() + "_" + incident.getType());
		countersRepo.increment(IncidentConstants.INCIDENT_TYPE_CATEGORY_PREFIX
				+ "_" + incident.getType() + "_" + incident.getCategory());
		countersRepo.increment(IncidentConstants.STATE_TOTAL_INCIDENTS_PREFIX
				+ "_" + incident.getState());
		countersRepo.increment(IncidentConstants.GOVT_TOTAL_INCIDENTS_PREFIX
				+ "_" + incident.getGovt());
		countersRepo
				.increment(IncidentConstants.INCIDENT_TYPE_TOTAL_INCIDENTS_PREFIX
						+ "_" + incident.getType());
		countersRepo
				.increment(IncidentConstants.STATE_GOVT_INCIDENT_TYPE_PREFIX
						+ "_" + incident.getState() + "_" + incident.getGovt()
						+ "_" + incident.getType());
		countersRepo.increment(IncidentConstants.STATE_GOVT_PREFIX + "_"
				+ incident.getState() + "_" + incident.getGovt());
		countersRepo
				.increment(IncidentConstants.STATE_INCIDENT_TYPE_CATEGORY_PREFIX
						+ "_"
						+ incident.getState()
						+ "_"
						+ incident.getType()
						+ "_" + incident.getCategory());

	}

	public void updateIncident(Incident incident) {

		oakCassendraTemplate.update(incident, Incident.class);

	}

	public void deleteIncidentById(IncidentKey id) {

		Incident incident = getIncidentById(id);
		oakCassendraTemplate.deleteById(id, Incident.class);
		countersRepo.decrement(IncidentConstants.STATE_INCIDENT_TYPE_PREFIX
				+ "_" + incident.getState() + "_" + incident.getType());
		countersRepo.decrement(IncidentConstants.GOVT_INCIDENT_TYPE_PREFIX
				+ "_" + incident.getGovt() + "_" + incident.getType());
		countersRepo.decrement(IncidentConstants.INCIDENT_TYPE_CATEGORY_PREFIX
				+ "_" + incident.getType() + "_" + incident.getCategory());
		countersRepo.decrement(IncidentConstants.STATE_TOTAL_INCIDENTS_PREFIX
				+ "_" + incident.getState());
		countersRepo.decrement(IncidentConstants.GOVT_TOTAL_INCIDENTS_PREFIX
				+ "_" + incident.getGovt());
		countersRepo
				.decrement(IncidentConstants.INCIDENT_TYPE_TOTAL_INCIDENTS_PREFIX
						+ "_" + incident.getType());
		countersRepo
				.decrement(IncidentConstants.STATE_GOVT_INCIDENT_TYPE_PREFIX
						+ "_" + incident.getState() + "_" + incident.getGovt()
						+ "_" + incident.getType());
		countersRepo.decrement(IncidentConstants.STATE_GOVT_PREFIX + "_"
				+ incident.getState() + "_" + incident.getGovt());
		countersRepo
				.decrement(IncidentConstants.STATE_INCIDENT_TYPE_CATEGORY_PREFIX
						+ "_"
						+ incident.getState()
						+ "_"
						+ incident.getType()
						+ "_" + incident.getCategory());
	}

}
