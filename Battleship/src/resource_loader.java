import java.io.InputStream;


public class resource_loader {
public static InputStream load(String path){
	InputStream input=resource_loader.class.getResourceAsStream(path);
	if(input==null)
	{
		input=resource_loader.class.getResourceAsStream("/"+path);
	}
	return input;
}
}
