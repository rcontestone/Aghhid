package com.rcons.fcallbacks.Model;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public class HouseMemberModel implements Serializable {

    private String district;
    private int mauza_id;
    private String mauza_name;
    private int hhid;
    private String previours_rounds;
    private int priority;
    private int tested;
    private String reason_for_leaving;
    private String zeli_zaat;
    private int mid;
    private String mem_name;
    private String name_rel_to_hh;
    private String previous_education;
    private String mem_age_r1;
    private String mem_age_r2;
    private String mem_age_r3;
    private String mem_age_r4;
    private String mem_age_r5;
    private String mem_marital_status_r5;
    private String school_name_r1;
    private String school_name_r2;
    private String school_name_r3;
    private String school_name_r4;
    private String school_name_r5;
    private String child_dad_id;
    private String child_mom_id;
    private String hh_hh_head_father;
    private String hh_hh_head_name;
    private String hh_settlement_name;
    private String neighbor_p1_name;
    private String neighbor_p1_fname;
    private String neighbor_p1_address;
    private String neighbor_p1_zaat;
    private String neighbor_p1_zaat_code;
    private String neighbor_p1_contact;
    private String neighbor_p2_name;
    private String neighbor_p2_fname;
    private String neighbor_p2_address;
    private String neighbor_p2_zaat;
    private String neighbor_p2_zaat_code;
    private String neighbor_p2_contact;

    public HouseMemberModel(){
        district = "";
        mauza_id = 0;
        mauza_name = "";
        hhid = 0;
        previours_rounds = "";
        priority = 0;
        tested = 0;
        reason_for_leaving = "";
        zeli_zaat = "";
        mid = 0;
        mem_name = "";
        name_rel_to_hh = "";
        previous_education = "";
        mem_age_r1 = "";
        mem_age_r2 = "";
        mem_age_r3 = "";
        mem_age_r4 = "";
        mem_age_r5 = "";
        mem_marital_status_r5 = "";
        school_name_r1 = "";
        school_name_r2 = "";
        school_name_r3 = "";
        school_name_r4 = "";
        school_name_r5 = "";
        child_dad_id = "";
        child_mom_id = "";
        hh_hh_head_father = "";
        hh_hh_head_name = "";
        hh_settlement_name = "";
        neighbor_p1_name = "";
        neighbor_p1_fname = "";
        neighbor_p1_address = "";
        neighbor_p1_zaat = "";
        neighbor_p1_zaat_code = "";
        neighbor_p1_contact = "";
        neighbor_p2_name = "";
        neighbor_p2_fname = "";
        neighbor_p2_address = "";
        neighbor_p2_zaat = "";
        neighbor_p2_zaat_code = "";
        neighbor_p2_contact = "";
    }



    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getMauza_id() {
        return mauza_id;
    }

    public void setMauza_id(int mauza_id) {
        this.mauza_id = mauza_id;
    }

    public String getMauza_name() {
        return mauza_name;
    }

    public void setMauza_name(String mauza_name) {
        this.mauza_name = mauza_name;
    }

    public int getHhid() {
        return hhid;
    }

    public void setHhid(int hhid) {
        this.hhid = hhid;
    }

    public String getPreviours_rounds() {
        return previours_rounds;
    }

    public void setPreviours_rounds(String previours_rounds) {
        this.previours_rounds = previours_rounds;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTested() {
        return tested;
    }

    public void setTested(int tested) {
        this.tested = tested;
    }

    public String getReason_for_leaving() {
        return reason_for_leaving;
    }

    public void setReason_for_leaving(String reason_for_leaving) {
        this.reason_for_leaving = reason_for_leaving;
    }

    public String getZeli_zaat() {
        return zeli_zaat;
    }

    public void setZeli_zaat(String zeli_zaat) {
        this.zeli_zaat = zeli_zaat;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getName_rel_to_hh() {
        return name_rel_to_hh;
    }

    public void setName_rel_to_hh(String name_rel_to_hh) {
        this.name_rel_to_hh = name_rel_to_hh;
    }

    public String getPrevious_education() {
        return previous_education;
    }

    public void setPrevious_education(String previous_education) {
        this.previous_education = previous_education;
    }

    public String getMem_age_r1() {
        return mem_age_r1;
    }

    public void setMem_age_r1(String mem_age_r1) {
        this.mem_age_r1 = mem_age_r1;
    }

    public String getMem_age_r2() {
        return mem_age_r2;
    }

    public void setMem_age_r2(String mem_age_r2) {
        this.mem_age_r2 = mem_age_r2;
    }

    public String getMem_age_r3() {
        return mem_age_r3;
    }

    public void setMem_age_r3(String mem_age_r3) {
        this.mem_age_r3 = mem_age_r3;
    }

    public String getMem_age_r4() {
        return mem_age_r4;
    }

    public void setMem_age_r4(String mem_age_r4) {
        this.mem_age_r4 = mem_age_r4;
    }

    public String getMem_age_r5() {
        return mem_age_r5;
    }

    public void setMem_age_r5(String mem_age_r5) {
        this.mem_age_r5 = mem_age_r5;
    }

    public String getMem_marital_status_r5() {
        return mem_marital_status_r5;
    }

    public void setMem_marital_status_r5(String mem_marital_status_r5) {
        this.mem_marital_status_r5 = mem_marital_status_r5;
    }

    public String getSchool_name_r1() {
        return school_name_r1;
    }

    public void setSchool_name_r1(String school_name_r1) {
        this.school_name_r1 = school_name_r1;
    }

    public String getSchool_name_r2() {
        return school_name_r2;
    }

    public void setSchool_name_r2(String school_name_r2) {
        this.school_name_r2 = school_name_r2;
    }

    public String getSchool_name_r3() {
        return school_name_r3;
    }

    public void setSchool_name_r3(String school_name_r3) {
        this.school_name_r3 = school_name_r3;
    }

    public String getSchool_name_r4() {
        return school_name_r4;
    }

    public void setSchool_name_r4(String school_name_r4) {
        this.school_name_r4 = school_name_r4;
    }

    public String getSchool_name_r5() {
        return school_name_r5;
    }

    public void setSchool_name_r5(String school_name_r5) {
        this.school_name_r5 = school_name_r5;
    }

    public String getChild_dad_id() {
        return child_dad_id;
    }

    public void setChild_dad_id(String child_dad_id) {
        this.child_dad_id = child_dad_id;
    }

    public String getChild_mom_id() {
        return child_mom_id;
    }

    public void setChild_mom_id(String child_mom_id) {
        this.child_mom_id = child_mom_id;
    }

    public String getHh_hh_head_father() {
        return hh_hh_head_father;
    }

    public void setHh_hh_head_father(String hh_hh_head_father) {
        this.hh_hh_head_father = hh_hh_head_father;
    }

    public String getHh_hh_head_name() {
        return hh_hh_head_name;
    }

    public void setHh_hh_head_name(String hh_hh_head_name) {
        this.hh_hh_head_name = hh_hh_head_name;
    }

    public String getHh_settlement_name() {
        return hh_settlement_name;
    }

    public void setHh_settlement_name(String hh_settlement_name) {
        this.hh_settlement_name = hh_settlement_name;
    }

    public String getNeighbor_p1_name() {
        return neighbor_p1_name;
    }

    public void setNeighbor_p1_name(String neighbor_p1_name) {
        this.neighbor_p1_name = neighbor_p1_name;
    }

    public String getNeighbor_p1_fname() {
        return neighbor_p1_fname;
    }

    public void setNeighbor_p1_fname(String neighbor_p1_fname) {
        this.neighbor_p1_fname = neighbor_p1_fname;
    }

    public String getNeighbor_p1_address() {
        return neighbor_p1_address;
    }

    public void setNeighbor_p1_address(String neighbor_p1_address) {
        this.neighbor_p1_address = neighbor_p1_address;
    }

    public String getNeighbor_p1_zaat() {
        return neighbor_p1_zaat;
    }

    public void setNeighbor_p1_zaat(String neighbor_p1_zaat) {
        this.neighbor_p1_zaat = neighbor_p1_zaat;
    }

    public String getNeighbor_p1_zaat_code() {
        return neighbor_p1_zaat_code;
    }

    public void setNeighbor_p1_zaat_code(String neighbor_p1_zaat_code) {
        this.neighbor_p1_zaat_code = neighbor_p1_zaat_code;
    }

    public String getNeighbor_p1_contact() {
        return neighbor_p1_contact;
    }

    public void setNeighbor_p1_contact(String neighbor_p1_contact) {
        this.neighbor_p1_contact = neighbor_p1_contact;
    }

    public String getNeighbor_p2_name() {
        return neighbor_p2_name;
    }

    public void setNeighbor_p2_name(String neighbor_p2_name) {
        this.neighbor_p2_name = neighbor_p2_name;
    }

    public String getNeighbor_p2_fname() {
        return neighbor_p2_fname;
    }

    public void setNeighbor_p2_fname(String neighbor_p2_fname) {
        this.neighbor_p2_fname = neighbor_p2_fname;
    }

    public String getNeighbor_p2_address() {
        return neighbor_p2_address;
    }

    public void setNeighbor_p2_address(String neighbor_p2_address) {
        this.neighbor_p2_address = neighbor_p2_address;
    }

    public String getNeighbor_p2_zaat() {
        return neighbor_p2_zaat;
    }

    public void setNeighbor_p2_zaat(String neighbor_p2_zaat) {
        this.neighbor_p2_zaat = neighbor_p2_zaat;
    }

    public String getNeighbor_p2_zaat_code() {
        return neighbor_p2_zaat_code;
    }

    public void setNeighbor_p2_zaat_code(String neighbor_p2_zaat_code) {
        this.neighbor_p2_zaat_code = neighbor_p2_zaat_code;
    }

    public String getNeighbor_p2_contact() {
        return neighbor_p2_contact;
    }

    public void setNeighbor_p2_contact(String neighbor_p2_contact) {
        this.neighbor_p2_contact = neighbor_p2_contact;
    }
}
