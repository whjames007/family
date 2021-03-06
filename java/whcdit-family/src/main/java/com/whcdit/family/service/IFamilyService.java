package com.whcdit.family.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.model.FamilyInfo;
import com.whcdit.family.model.FamilyMember;
import com.whcdit.family.model.FamilyRichesRecord;

public interface IFamilyService {

	public int add(FamilyInfo param);
	
	public int del(FamilyInfo param);
	
	public int edit(FamilyInfo param);
	
	public List<FamilyInfo> list(FamilyInfo param);
	
	public PageInfo<FamilyInfo> page(FamilyInfo param);

	public boolean repeat(FamilyInfo param);

	public PageInfo<FamilyInfo> search(FamilyInfo param);

	public FamilyMember join(FamilyMember param);

	public PageInfo<FamilyRichesRecord> richesPage(FamilyRichesRecord param);

	public void richesAdd(FamilyRichesRecord param);

	public void richesEdit(FamilyRichesRecord param);

}
