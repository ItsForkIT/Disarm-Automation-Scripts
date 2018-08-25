import ie.wombat.jbdiff.JBPatch;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class DiffUtils {


//    public static void createDiff(File source, File destination) throws IOException {
//        File delta = Environment.getExternalStoragePublicDirectory("/DMS/Working/SurakshitDiff/"+getDeltaName(destination));
//        try {
//            JBDiff.bsdiff(source, destination, delta);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    public static void applyPatch(File source, File delta, File latestDestDir) {
        if (delta != null) {
            String deltaName = FilenameUtils.getBaseName(delta.getName());
            File destination = new File(latestDestDir + "/" + deltaName + ".kml");
            try {
                JBPatch.bspatch(source, destination, delta);
                System.out.println("Patching Succeeded");
//                FileUtils.forceDelete(delta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//
//    private static String getDeltaName(File source) throws IOException {
//        File diffDir = Environment.getExternalStoragePublicDirectory("DMS/Working/SurakshitDiff");
//        KmlDocument kml = new KmlDocument();
//        kml.parseKMLFile(source);
//        String name = FilenameUtils.getBaseName(source.getName());
//        int version = Integer.parseInt(kml.mKmlRoot.getExtendedData("total"))-1;
//        return name+"_"+version+".diff";
//    }

//    private static File getDestinationFile(File delta) {
//        String deltaName = FilenameUtils.getBaseName(delta.getName());
//        return Environment.getExternalStoragePublicDirectory("DMS/KML/Dest/LatestKml/" + deltaName + ".kml");
//    }
}
