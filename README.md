# DISARM Automation Scripts


MCS Decrypter
--------

To run the script follow the steps:

1. Copy the entire `MCSDecrypter_jar` directory to a suitable location and cd into the directory
    ```
    cd Disarm-Automation-Scripts/MCSDecrypter/out/artifacts/MCSDecrypter_jar
    ```
3. Output Folder should contain a folder named tempDecrypt
2. Run the MCSDecrypter.jar script
	```
    java -jar MCSDecrypter.jar <path to /Working/SurakshitKml directory> <decrypted destination directory>  <path to volunteer private key> <path to volunteer public key>
    ```
    Example:
    ```
    java -jar MCSDecrypter.jar /home/bishakh/DMS/Working/SurakshitKml /home/bishakh/DMS/decrypted /home/bishakh/DMS/Working/pgpKey/pri_volunteer.bgp /home/bishakh/DMS/Working/pgpKey/pub_volunteer.bgp
    ```


DiffPatcher
-----------
```
java -jar DiffPatcher.jar <path to SurakshitDiff> < path to LatestKML Output>  < path to SurakshitKml - decrypted folder>
```