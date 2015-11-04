/* This is an example step groovy to show the proper use of APTool
 * In order to use import these utilities, you have to use the "pluginutilscripts" jar
 * that comes bundled with this plugin example. 
 */
import com.urbancode.air.AirPluginTool;
import com.urbancode.air.CommandHelper;

/* This gets us the plugin tool helper. 
 * This assumes that args[0] is input props file and args[1] is output props file.
 * By default, this is true. If your plugin.xml looks like the example. 
 * Any arguments you wish to pass from the plugin.xml to this script that you don't want to 
 * pass through the step properties can be accessed using this argument syntax
 */
def apTool = new AirPluginTool(this.args[0], this.args[1]) 

/* Here we call getStepProperties() to get a Properties object that contains the step properties
 * provided by the user. 
 */
def props = apTool.getStepProperties();

/* This is how you retrieve properties from the object. You provide the "name" attribute of the 
 * <property> element 
 * 
 */
def dirOffset = props['dirOffset'];
def fileNames = props['fileNames'];
def exist = true;
def directoryOffset = new File(dirOffset).getCanonicalPath();

def fileNameList = fileNames.readLines();
for(def fileName : fileNameList) {
	exist = new File(directoryOffset+File.separator+fileName).exists();
	if(!exist) {
		println directoryOffset + File.separator + fileName + ": does not exist";
		break;
	} else {
		println directoryOffset + File.separator + fileName + ": exists";
	}
}

if(exist)
	System.exit(0)
else
	System.exit(-1);

apTool.storeOutputProperties();//write the output properties to the file
