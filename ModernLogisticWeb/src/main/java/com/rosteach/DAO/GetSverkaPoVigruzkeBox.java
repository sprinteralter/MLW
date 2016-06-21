package com.rosteach.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.rosteach.entities.SverkaPoVigruzkeBox;

@Repository
public class GetSverkaPoVigruzkeBox {
	private JdbcTemplate jdbcTemplate = null;
	
	@Autowired	
	public GetSverkaPoVigruzkeBox(@Qualifier("dataSourceSprinter") DataSource dataSourceSprinter) throws SQLException{
		this.jdbcTemplate = (new JdbcTemplate(dataSourceSprinter));
	}
	
	public List<SverkaPoVigruzkeBox> getDataByClient(java.sql.Date startDate, java.sql.Date endDate){
		final String query = "select "+
	    	"sv.clientsname,"+
	    	"sum(svt.itemcount),"+
	    	"sum(svt.itemcountdone)"+
	    "from "+
	    	"SPRORDERSOUTINV (1,'"+startDate+"','"+endDate+"',0,0,0) sv,"+
	    	"SPRORDERSOUTINVDET (sv.id,Null,0,2,1, Null,Null,0,0) svt "+
	    "group by "+
	    	"sv.clientsname";
		
		List<SverkaPoVigruzkeBox> sverkaList= this.jdbcTemplate.query(query, new RowMapper<SverkaPoVigruzkeBox>(){
			public SverkaPoVigruzkeBox mapRow(ResultSet resultSet, int rowNum) throws SQLException{
				SverkaPoVigruzkeBox sverka = new SverkaPoVigruzkeBox();
				sverka.setClientsName(resultSet.getString("CLIENTSNAME"));
				sverka.setOrder(resultSet.getDouble(2));
				sverka.setShipment(resultSet.getDouble(3));
				return sverka;
			} 
		});
		return sverkaList;
	}
}
