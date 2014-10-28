package com.kricko.tvshowupdater.utils;

import java.util.List;

/**
 *
 * @author bvidovic
 * @version $Revision: 1.0 $
 */
public class CompressionDefinition {

    String command, encode_to_ext, name;
    List<String> verificationLines;
    /**
     * Constructor for CompressionDefinition.
     * @param name String
     * @param command String
     * @param encodeToExt String
     * @param verificationLines List<String>
     */
    public CompressionDefinition(String name, String command, String encodeToExt, List<String> verificationLines)
    {
        this.name = name;
        this.command = command;
        if(valid(encodeToExt))//normalize the ext. Should not start with dot
        {
            encodeToExt = encodeToExt.trim();
            while(encodeToExt.startsWith("."))
                encodeToExt = encodeToExt.substring(1, encodeToExt.length());
        }
        this.encode_to_ext = encodeToExt;
        this.verificationLines=verificationLines;
       
    }
    /**
     * Method getVerificationLines.
     * @return List<String>
     */
    public List<String> getVerificationLines()
    {
        return verificationLines;
    }
    /**
     * Method getCommand.
     * @return String
     */
    public String getCommand()
    {
        return command;
    }
    /**
     * Method getEncodeToExt.
     * @return String
     */
    public String getEncodeToExt()
    {
        return encode_to_ext;
    }
   
    /**
     * Method getName.
     * @return String
     */
    public String getName()
    {
        return name;
    }

    //case-insensitive matching based on name
    /**
     * Method equals.
     * @param o Object
     * @return boolean
     */
    public boolean equals(Object o)
    {
        if(!(o instanceof CompressionDefinition)) return false;
        return getName().equalsIgnoreCase( ((CompressionDefinition)o).getName());
    }

    /**
     * Method hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.name != null ? this.name.toLowerCase().hashCode() : 0);//always LC. Case-insensitive equals
        return hash;
    }

    /**
     * Method valid.
     * @param s String
     * @return boolean
     */
    private static boolean valid(String s)
    {
        return s != null && !s.trim().isEmpty();
    }
}

