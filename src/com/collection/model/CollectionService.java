package com.collection.model;

import java.util.ArrayList;
import java.util.List;

public class CollectionService {
	private CollectionDAO_interface dao;

	public CollectionService() {
		dao = new CollectionDAO();
	}

	public CollectionVO addCollection(String mem_no, String all_no, String class_no) {

		CollectionVO collectionVO = new CollectionVO();

		collectionVO.setMem_no(mem_no);
		collectionVO.setAll_no(all_no);
		collectionVO.setClass_no(class_no);
		dao.insert(collectionVO);

		return collectionVO;
	}

	public CollectionVO updateCollection(String coll_no, String mem_no, String all_no, String class_no) {

		CollectionVO collectionVO = new CollectionVO();

		collectionVO.setColl_no(coll_no);
		collectionVO.setMem_no(mem_no);
		collectionVO.setAll_no(all_no);
		collectionVO.setClass_no(class_no);
		dao.update(collectionVO);

		return collectionVO;
	}

	public void deleteCollection(String coll_no) {
		dao.delete(coll_no);
	}

	public CollectionVO getOneCollection(String coll_no) {
		return dao.findByPrimaryKey(coll_no);
	}
	
	public List<CollectionVO> getAllByMem_noCollection(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
	
	public List<CollectionVO> getAll() {
		return dao.getAll();
	}
	

	public CollectionVO findByMem_noAndAll_no(String mem_no, String all_no){
		return dao.findByMem_noAndAll_no(mem_no, all_no);
  }

	//拿到收藏的食譜
	public List<CollectionVO> getMyRecipeCollection(String mem_no) {
		List<CollectionVO> list = dao.findByMem_no(mem_no); 
		List<CollectionVO> list2 =new ArrayList<CollectionVO>();
		
		for(CollectionVO aCollect: list){
			if(aCollect.getClass_no().equals("R")){
				list2.add(aCollect);
			}
		}
		return list2;
	}
	
	//拿到追隨的作者
	public List<CollectionVO> getMyAuthorCollection(String mem_no) {
		List<CollectionVO> list = dao.findByMem_no(mem_no); 
		List<CollectionVO> list2 =new ArrayList<CollectionVO>();
		
		for(CollectionVO aCollect: list){
			if(aCollect.getClass_no().equals("M")){
				list2.add(aCollect);
			}
		}
		return list2;
	}
	
	//拿到追隨的私廚
	public List<CollectionVO> getMyChefCollection(String mem_no) {
		List<CollectionVO> list = dao.findByMem_no(mem_no); 
		List<CollectionVO> list2 =new ArrayList<CollectionVO>();
		
		for(CollectionVO aCollect: list){
			if(aCollect.getClass_no().equals("C")){
				list2.add(aCollect);
			}
		}
		return list2;
	}
	public int getCollectionSize(String all_no){
		return dao.getCollectionSize(all_no);
	}
}
