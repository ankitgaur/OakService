package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Incident;
import com.oak.entities.IncidentKey;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("incidentRepo")
public class IncidentRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

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

	}

	public void updateIncident(Incident incident) {

		oakCassendraTemplate.update(incident, Incident.class);

	}

	public void deleteIncidentById(IncidentKey id) {
		oakCassendraTemplate.deleteById(id, Incident.class);
	}

}
