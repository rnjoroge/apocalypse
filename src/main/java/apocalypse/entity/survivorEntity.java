package apocalypse.entity;
/**
 * @author robertnjoroge
 */



/**
*
* survivor class to handle json mapping from incoming request
*/


import java.util.List;

public class survivorEntity {

	   
	   private String name;
	   private int age;
	   private String gender;
	   private String id;
	   private List<Double> last_location;
	   private String [] resources;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}





	public String [] getResources() {
		return resources;
	}


	public void setResources(String [] resources) {
		this.resources = resources;
	}


	public List<Double> getLast_location() {
		return last_location;
	}


	public void setLast_location(List<Double> last_location) {
		this.last_location = last_location;
	}

}
