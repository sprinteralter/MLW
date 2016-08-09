package com.rosteach.util;

import java.util.LinkedList;
import java.util.List;

import com.rosteach.entities.COMDOC;
import com.rosteach.entities.COMDOC.Parameters.Parameter;
import com.rosteach.entities.COMDOC.Sides.Contractor;
import com.rosteach.entities.COMDOC.Table.Row;
import com.rosteach.entities.COMDOC.Table.Row.Code;
import com.rosteach.entities.COMDOC.Table.Row.Totaltable;

public class COMDOCUtil {
	public static COMDOC.Header getHeader(){
		return new COMDOC.Header("0001","Накладна","007",null,"444",
				new COMDOC.Header.Comment("ghbdtn", 1),new COMDOC.Header.Docreason("000","Видаткова накладна","006",null));
	}
	public static COMDOC.Sides getSides(){
		List<Contractor> contractors = new LinkedList<Contractor>();
		contractors.add(new Contractor("Покупець","Юридична","Товариство з обмеженою відповідальністю 'Фудмережа'",0,0,0,0));
		contractors.add(new Contractor("Продавець","Юридична","Товариство з обмеженою відповідальністю 'Спринтер К'",0,0,0,0));
		return new COMDOC.Sides(contractors);
	}
	public static COMDOC.Parameters getParameters(){
		List<Parameter> parameters = new LinkedList<Parameter>();
		parameters.add(new Parameter("0",1,"Точка доставки"));
		parameters.add(new Parameter("0",2,"Адреса доставки"));
		parameters.add(new Parameter("0",3,"Номер договору"));
		parameters.add(new Parameter("0",4,"Дата договору"));
		parameters.add(new Parameter("0",5,"Тара"));
		return new COMDOC.Parameters(parameters);
	}
	public static COMDOC.Table getTable(){
		List<Row> rows = new LinkedList<Row>();
		rows.add(new Row(1,new Code("0",1),1,"Найменування",2.0,"i",0.0,0.0,0.0,new Totaltable(0.0,0.0,0.0),1));
		return new COMDOC.Table(rows);
	}
	public static COMDOC.Documenttotal getDocTotal(){
		return new COMDOC.Documenttotal(20.0,20.0,20.0);
	}
}
