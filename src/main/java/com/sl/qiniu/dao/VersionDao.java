package com.sl.qiniu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VersionDao {

	public int save(Map<String, Object> map);

	public int update(Map<String, Object> map);

	public int delete(Map<String, Object> map);

	public ArrayList<HashMap<String, Object>> query(Map<String, Object> map);

}
