/**
 * @filename:${entityName}Mapper ${createTime}
 * @project ${project}  ${version}
 * Copyright(c) 2018 ${author} Co. Ltd. 
 * All right reserved. 
 */
package ${daoUrl};

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ${entityUrl}.${entityName};

/**   
 *  
 * @Description:  ${entityComment}——Mapper
 * @Author:       ${author}   
 * @CreateDate:   ${createTime}
 * @Version:      ${version}
 *    
 */
@Mapper
public interface ${entityName}Mapper {
	
	public ${entityName} selectByPrimaryKey(${idType} id);
	
	public int deleteByPrimaryKey(${idType} id);
	
	public int insertSelective(${entityName} ${objectName});
	
	public int updateByPrimaryKeySelective(${entityName} ${objectName});
	
	public List<${entityName}> query${entityName}List(${entityName} ${objectName});
}
	