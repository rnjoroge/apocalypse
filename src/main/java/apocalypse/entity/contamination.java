package apocalypse.entity;
/**
 * @author robertnjoroge
 */


/**
*
* contamination class to handle json mapping from incoming request
*/

public class contamination {
	
	   private String reporter;
	   private String robot_serial_number;
	   private String report_details;
	   
	   
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getRobot_serial_number() {
		return robot_serial_number;
	}
	public void setRobot_serial_number(String robot_serial_number) {
		this.robot_serial_number = robot_serial_number;
	}

	public String getReport_details() {
		return report_details;
	}
	public void setReport_details(String report_details) {
		this.report_details = report_details;
	}
	  

}
