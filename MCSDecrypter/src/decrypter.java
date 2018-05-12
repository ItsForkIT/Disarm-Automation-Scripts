import java.io.File;

public class decrypter {

    static String sourcePath;
    static String destPath;
    static String volunteerKeyPath;

static Runnable decrypter  = new Runnable() {
    @Override
    public void run() {
        while(true){
            File dir = new File(sourcePath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {

                    if(child.getName().contains("volunteer")){
                        File destFile = new File(destPath + File.separator + child.getName().replace("bgp", "kml"));
                        if(!destFile.exists()) {
                            System.out.println(child.getAbsolutePath());
                            try {
                                KeyBasedFileProcessor.decrypt(child.getAbsolutePath(), volunteerKeyPath, "volunteer@disarm321", destPath);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            System.out.println("SKIPPING AS EXISTS: " + child.getAbsolutePath());
                        }

                    }
                }
            } else {

            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
};




public static void main(String args[]){
//    sourcePath = args[0];
//    destPath = args[1];
    sourcePath = "/home/bishakh/DMS/sync/SurakshitKml";
    destPath = "/home/bishakh/DMS/decrypted";
    volunteerKeyPath = "/home/bishakh/DMS/volunteer_pri.bgp";



    // Decryption Test

//    try {
//        KeyBasedFileProcessor.decrypt("/sdcard/video.mp4.bpg", "/sdcard/secret.bpg", "123");
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
    Thread decrypterThread = new Thread(decrypter);
    decrypterThread.start();



System.out.println("Hooa");
}


}
