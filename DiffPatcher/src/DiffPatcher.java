import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.HashMap;

public class DiffPatcher {
    public static void main(String[] args) {
        File diffDir = new File(args[0]);
        File latestDir = new File(args[1]);
        File sourceDir = new File(args[2]);
        while (true) {

            try {
                patcher(diffDir, latestDir, sourceDir);

            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void patcher(File diffDir, File latestDestDir, File sourceDestDir) {
        File[] diffFiles = diffDir.listFiles();
        HashMap<String, File> myDiffFiles = new HashMap<>();
        HashMap<String, Integer> latestVersion = new HashMap<>();
        if (diffFiles == null) {
            System.out.println("No files found");
            return;
        }
        for (File file : diffFiles) {
            String[] name = FilenameUtils.getBaseName(file.getName()).split("_");
            if (!latestVersion.containsKey(name[0])) {
                latestVersion.put(name[0], Integer.parseInt(name[4]));
            }
            if (Integer.parseInt(name[4]) >= latestVersion.get(name[0])) {
                myDiffFiles.put(name[0], file);
                latestVersion.put(name[0], Integer.parseInt(name[4]));
            }
        }

//        HashMap<String, File> sourceFiles = new HashMap<>();
//        for (File file : sourceDestDir.listFiles()) {
//            String[] name = file.getName().split("_");
//            sourceFiles.put(name[0], file);
//        }
//        for (File file : latestDestDir.listFiles()) {
//            String fileName = FilenameUtils.getBaseName(file.getName());
//            String split[] = fileName.split("_");
//            if (myDiffFiles.containsKey(split[0])) {
//                System.out.println("Found Diff Files");
//                File diff = myDiffFiles.get(split[0]);
//                int version = Integer.parseInt(FilenameUtils.getBaseName(diff.getName()).split("_")[4]);
////                int curr_version = Integer.parseInt(split[4]);
//                File source = sourceFiles.get(split[0]);
//                DiffUtils.applyPatch(source, diff, latestDestDir);
//            }
//        }

        for (File file : sourceDestDir.listFiles()) {
            String[] name = file.getName().split("_");
            if (myDiffFiles.containsKey(name[0])) {
                File diff = myDiffFiles.get(name[0]);
                DiffUtils.applyPatch(file, diff, latestDestDir);
            }
        }
    }
}
