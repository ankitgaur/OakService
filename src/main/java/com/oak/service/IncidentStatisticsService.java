package com.oak.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.constants.incidents.IncidentStatisticsConstants;
import com.oak.entities.IncidentStatistics;
import com.oak.entities.IncidentStatisticsKey;
import com.oak.repositories.IncidentStatisticsRepo;
import com.oak.vo.IncidentTypeStat;
import com.oak.vo.MultiDimStat;
import com.oak.vo.RegionStat;

@Service("incidentStatisticsService")
public class IncidentStatisticsService {

	@Autowired
	private IncidentStatisticsRepo incidentStatisticsRepo;

	public List<IncidentStatistics> getIncidentStatistics(String name) {
		return incidentStatisticsRepo.getStatistics(name);
	}

	public void incrementStatsCounter(String type, String cat, String state, String govt) {

		/*Note : We could have kept the values for all the columns and made only one statistics name.
		 * However this would have retrieved multiple rows for name column value. for eg. multiple rows 
		 * for same incident type 'CRIME'. This would have made a group by necessary. Thus to save time consumed 
		 * by a group by we have adopted the below design*/
		
		// increment incident count for each state
		IncidentStatisticsKey key1 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_COUNT_FOR_EACH_STATE, 
																	IncidentStatisticsConstants.NO_VALUE,
																	IncidentStatisticsConstants.NO_VALUE, 
																	state, 
																	IncidentStatisticsConstants.NO_VALUE);

		// increment incident type count for each state
		IncidentStatisticsKey key2 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_EACH_STATE, 
																type, 
																IncidentStatisticsConstants.NO_VALUE, 
																state, 
																IncidentStatisticsConstants.NO_VALUE);
		
		// increment incident cat count for each state
		IncidentStatisticsKey key3 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_CAT_COUNT_FOR_EACH_STATE, 
				type, 
				cat, 
				state, 
				IncidentStatisticsConstants.NO_VALUE);


		// increment incident count for govt of each state
		IncidentStatisticsKey key4 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_COUNT_FOR_GOVT_EACH_STATE, 
				IncidentStatisticsConstants.NO_VALUE, 
				IncidentStatisticsConstants.NO_VALUE, 
				state, 
				govt);


		// increment incident type count for govt of each state
		IncidentStatisticsKey key5 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_GOVT_EACH_STATE, 
				type, 
				IncidentStatisticsConstants.NO_VALUE, 
				state, 
				govt);


		// increment incident cat count for govt of each state
		IncidentStatisticsKey key6 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_CAT_COUNT_FOR_GOVT_EACH_STATE, 
				type, 
				cat, 
				state, 
				govt);
		
		incidentStatisticsRepo.increment(key1);
		incidentStatisticsRepo.increment(key2);
		incidentStatisticsRepo.increment(key3);
		incidentStatisticsRepo.increment(key4);
		incidentStatisticsRepo.increment(key5);
		incidentStatisticsRepo.increment(key6);

	}

	public void decrementStatsCounter(String type, String cat, String state, String govt) {
		/*Note : We could have kept the values for all the columns and made only one statistics name.
		 * However this would have retrieved multiple rows for name column value. for eg. multiple rows 
		 * for same incident type 'CRIME'. This would have made a group by necessary. Thus to save time consumed 
		 * by a group by we have adopted the below design*/
		
		// increment incident count for each state
		IncidentStatisticsKey key1 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_COUNT_FOR_EACH_STATE, 
																	IncidentStatisticsConstants.NO_VALUE,
																	IncidentStatisticsConstants.NO_VALUE, 
																	state, 
																	IncidentStatisticsConstants.NO_VALUE);

		// increment incident type count for each state
		IncidentStatisticsKey key2 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_EACH_STATE, 
																type, 
																IncidentStatisticsConstants.NO_VALUE, 
																state, 
																IncidentStatisticsConstants.NO_VALUE);
		
		// increment incident cat count for each state
		IncidentStatisticsKey key3 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_CAT_COUNT_FOR_EACH_STATE, 
				type, 
				cat, 
				state, 
				IncidentStatisticsConstants.NO_VALUE);


		// increment incident count for govt of each state
		IncidentStatisticsKey key4 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_COUNT_FOR_GOVT_EACH_STATE, 
				IncidentStatisticsConstants.NO_VALUE, 
				IncidentStatisticsConstants.NO_VALUE, 
				state, 
				govt);


		// increment incident type count for govt of each state
		IncidentStatisticsKey key5 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_EACH_STATE, 
				type, 
				IncidentStatisticsConstants.NO_VALUE, 
				state, 
				govt);


		// increment incident cat count for govt of each state
		IncidentStatisticsKey key6 = new IncidentStatisticsKey(IncidentStatisticsConstants.INCI_CAT_COUNT_FOR_GOVT_EACH_STATE, 
				type, 
				cat, 
				state, 
				govt);
		
		incidentStatisticsRepo.decrement(key1);
		incidentStatisticsRepo.decrement(key2);
		incidentStatisticsRepo.decrement(key3);
		incidentStatisticsRepo.decrement(key4);
		incidentStatisticsRepo.decrement(key5);
		incidentStatisticsRepo.decrement(key6);
	}

	/**
	 * This method returns incident count for each state
	 * 
	 * @return
	 */
	public List<RegionStat> getRegionStats() {
	
		List<RegionStat> rsl = new ArrayList<RegionStat>();
		List<IncidentStatistics> isl = getIncidentStatistics(IncidentStatisticsConstants.INCI_COUNT_FOR_EACH_STATE);
		
		for(IncidentStatistics is:isl){
			RegionStat rs = new RegionStat();
			rs.setName(is.getKey().getState());
			rs.setCount(is.getVal());
			rsl.add(rs);
		}
		
		
		return rsl;
	}

	public List<IncidentTypeStat> getIncidentTypeStats() {
		// TODO I think it might not be used
		return null;
	}

	/**
	 * This method returns incident count for a particular state
	 * 
	 * @param region
	 * @return
	 */
	public List<IncidentTypeStat> getIncidentTypeStats(String region) {
		
		List<IncidentTypeStat> rsl = new ArrayList<IncidentTypeStat>();
		List<IncidentStatistics> isl = incidentStatisticsRepo.getStatisticsForState(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_EACH_STATE,region);
		
		for(IncidentStatistics is:isl){
			
			
				IncidentTypeStat rs = new IncidentTypeStat();
				rs.setName(is.getKey().getType());
				rs.setCount(is.getVal());
				rsl.add(rs);
			
			
		}
		
		
		return rsl;
	}

	/**
	 * This method returns the incident category count for an incident type and region
	 * 
	 * @param region
	 * @param type
	 * @return
	 */
	public List<IncidentTypeStat> getIncidentTypeStatsForState(String region, String type) {
		List<IncidentTypeStat> rsl = new ArrayList<IncidentTypeStat>();
		List<IncidentStatistics> isl = incidentStatisticsRepo.getStatisticsForStateAndType(IncidentStatisticsConstants.INCI_CAT_COUNT_FOR_EACH_STATE,type,region);
		
		for(IncidentStatistics is:isl){
			IncidentTypeStat rs = new IncidentTypeStat();
			rs.setName(is.getKey().getCat());
			rs.setCount(is.getVal());
			rsl.add(rs);
		}
		
		
		return rsl;
	}

	public List<IncidentTypeStat> getIncidentTypeForAllStates(String type) {
		
		List<IncidentTypeStat> rsl = new ArrayList<IncidentTypeStat>();
		List<IncidentStatistics> isl = getIncidentStatistics(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_EACH_STATE);
		
		for(IncidentStatistics is:isl){

			if(is.getKey().getType().equals(type)){
				IncidentTypeStat rs = new IncidentTypeStat();
				rs.setName(is.getKey().getState());
				rs.setCount(is.getVal());
				rsl.add(rs);
			}
			
		}
		
		
		return rsl;
		
	}

	public List<IncidentTypeStat> getInciCatStats(String type, String cat) {
		List<IncidentTypeStat> rsl = new ArrayList<IncidentTypeStat>();
		List<IncidentStatistics> isl = getIncidentStatistics(IncidentStatisticsConstants.INCI_CAT_COUNT_FOR_EACH_STATE);
		
		for(IncidentStatistics is:isl){
			if(is.getKey().getType().equals(type) && is.getKey().getCat().equals(cat)){
				IncidentTypeStat rs = new IncidentTypeStat();
				rs.setName(is.getKey().getState());
				rs.setCount(is.getVal());
				rsl.add(rs);
			}
		}
		
		
		return rsl;
	}

	public List<MultiDimStat> getGovtStatsForState(String region) {
		List<MultiDimStat> rsl = new ArrayList<MultiDimStat>();
		List<IncidentStatistics> isl = getIncidentStatistics(IncidentStatisticsConstants.INCI_TYPE_COUNT_FOR_GOVT_EACH_STATE);
		
		for(IncidentStatistics is:isl){
			if(is.getKey().getState().equals(region)){
				MultiDimStat rs = new MultiDimStat();
				rs.setName(is.getKey().getGovt());
				rs.setCount(is.getVal());
				rs.setType(is.getKey().getType());
				rsl.add(rs);
			}
			
		}
		
		
		return rsl;
	}
	
	
}