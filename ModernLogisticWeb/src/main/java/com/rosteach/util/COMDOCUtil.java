package com.rosteach.util;

import java.util.LinkedList;
import java.util.List;

import com.rosteach.entities.COMDOC;
import com.rosteach.entities.COMDOC.Sides.Contractor;

public class COMDOCUtil {
	public static COMDOC.Header getHeader(){
		return new COMDOC.Header("0001","Накладна","007",null,"444",
				new COMDOC.Header.Comment("ghbdtn", 1),new COMDOC.Header.Docreason("000","Видаткова накладна","006",null));
	}
	public static COMDOC.Sides getSides(){
		List<Contractor> contractor = new LinkedList<Contractor>();
		contractor.add(new Contractor("Покупець","Юридична","Товариство з обмеженою відповідальністю 'Фудмережа'",0,0,0,0));
		contractor.add(new Contractor("Продавець","Юридична","Товариство з обмеженою відповідальністю 'Спринтер К'",0,0,0,0));
		return new COMDOC.Sides(contractor);
	}
	public static COMDOC.Parameters getParameters(){
		return new COMDOC.Parameters();
	}
	public static COMDOC.Table getTable(){
		return new COMDOC.Table();
	}
	public static COMDOC.Documenttotal getDocTotal(){
		return new COMDOC.Documenttotal(20.0,20.0,20.0);
	}
}
