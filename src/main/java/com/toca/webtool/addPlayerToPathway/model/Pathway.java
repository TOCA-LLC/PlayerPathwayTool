package com.toca.webtool.addPlayerToPathway.model;

public class Pathway {

    private long playerId;
    private String pathwayName;
    private long siteId;
    private long pathwayId;

    public Pathway(long playerId, String pathwayName, long siteId, long pathwayId) {
        this.playerId = playerId;
        this.pathwayName = pathwayName;
        this.siteId = siteId;
        this.pathwayId = pathwayId;
    }

    public Pathway() {
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getPathwayName() {
        return pathwayName;
    }

    public long getSiteId() {
        return siteId;
    }

    public long getPathwayId() {
        return pathwayId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setPathwayName(String pathwayName) {
        String newPathwayName = null;
        if(pathwayName.contains("Beginner Intensive")){
            newPathwayName = "Beginner Intensive";
        }
        else if(pathwayName.contains("Intermediate Intensive")){
            newPathwayName = "Intermediate Intensive";
        }
        else if(pathwayName.contains("Advanced Intensive")){
            newPathwayName = "Advanced Intensive";
        }
        this.pathwayName = newPathwayName;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public void setPathwayId(long pathwayId) {
        this.pathwayId = pathwayId;
    }

    public void getData(){
        System.out.println("Pathway Name: " + pathwayName);
        System.out.println("Pathway ID: " + pathwayId);
        System.out.println("Player ID: " + playerId);
        System.out.println("Site ID: " + siteId + "\n");
    }

}