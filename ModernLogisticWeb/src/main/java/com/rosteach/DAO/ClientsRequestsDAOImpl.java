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

import com.rosteach.entities.ClientsRequests;

@Repository
public class ClientsRequestsDAOImpl implements ClientsRequestsDAO{
	
	private JdbcTemplate jdbcTemplate = null;
	
	@Autowired
	public ClientsRequestsDAOImpl(@Qualifier("dataSourceAlter")DataSource dataSource) throws SQLException{
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}
	@Override
	public List<ClientsRequests> getAllRequests() {
		final String query = "select * from SPRORDERSOUTINV (1,'09.05.2016',Null,0,Null,0)";
		List<ClientsRequests> requests = this.jdbcTemplate.query(query, new RowMapper<ClientsRequests>(){
			public ClientsRequests mapRow(ResultSet resultSet, int rowNum) throws SQLException{
				ClientsRequests clientsRequests = new ClientsRequests();
				clientsRequests.setId(resultSet.getInt("ID"));
				clientsRequests.setDocdate(resultSet.getDate("DOCDATE"));
				clientsRequests.setClientid(resultSet.getInt("CLIENTID"));
				clientsRequests.setClientsname(resultSet.getString("CLIENTSNAME"));
				clientsRequests.setClientname(resultSet.getString("CLIENTNAME"));
				clientsRequests.setStoreid(resultSet.getInt("STOREID"));
				clientsRequests.setStoresname(resultSet.getString("STORESNAME"));
				clientsRequests.setStorename(resultSet.getString("STORENAME"));
				clientsRequests.setOutcomeinvoiceidsset(resultSet.getString("OUTCOMEINVOICEIDSSET"));
				clientsRequests.setEndsumm(resultSet.getDouble("ENDSUMM"));
				clientsRequests.setEndsummwithoverh(resultSet.getDouble("ENDSUMMWITHOVERH"));
				clientsRequests.setComment(resultSet.getString("comment"));
				clientsRequests.setOverhsumm(resultSet.getDouble("OVERHSUMM"));
				clientsRequests.setBeepreslinkid(resultSet.getInt("BEEPRESLINKID"));
				clientsRequests.setBeepresstore(resultSet.getString("BEEPRESSTORE"));
				clientsRequests.setBeepressendtime(resultSet.getTimestamp("BEEPRESSENDTIME"));
				clientsRequests.setTermdate(resultSet.getDate("TERMDATE"));
				clientsRequests.setPaytypeid(resultSet.getInt("PAYTYPEID"));
				clientsRequests.setPaytypesname(resultSet.getString("PAYTYPESNAME"));
				clientsRequests.setComment1(resultSet.getString("COMMENT1"));
				clientsRequests.setComment2(resultSet.getString("COMMENT2"));
				clientsRequests.setPricetypeid(resultSet.getInt("PRICETYPEID"));
				clientsRequests.setPricetypesname(resultSet.getString("PRICETYPESNAME"));
				clientsRequests.setUseredit(resultSet.getString("USEREDIT"));
				clientsRequests.setTimeedit(resultSet.getTimestamp("TIMEEDIT"));
				clientsRequests.setAgentid(resultSet.getInt("AGENTID"));
				clientsRequests.setAgentsname(resultSet.getString("AGENTSNAME"));
				clientsRequests.setItemssumm(resultSet.getDouble("ITEMSSUMM"));
				clientsRequests.setDiscount(resultSet.getFloat("DISCOUNT"));
				clientsRequests.setSpecdiscount(resultSet.getFloat("SPECDISCOUNT"));
				clientsRequests.setOk_passed(resultSet.getInt("OK_PASSED"));
				clientsRequests.setClientadresslocation(resultSet.getString("CLIENTADRESSLOCATION"));
				clientsRequests.setProfilesname(resultSet.getString("PROFILESNAME"));
				return clientsRequests;
			} 
		});
		return requests;
	}
}
