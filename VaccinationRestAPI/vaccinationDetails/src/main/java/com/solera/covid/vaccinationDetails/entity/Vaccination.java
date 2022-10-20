package com.solera.covid.vaccinationDetails.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Vaccination")
@SecondaryTable(name = "VAX_DETAILS", pkJoinColumns = @PrimaryKeyJoinColumn(name ="HEALTH_ID"))
public class Vaccination {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="curse_seq")
    @SequenceGenerator(
        name="curse_seq",
        sequenceName="helathID",
        allocationSize=1
    )
	private int healthId; 

	@Column(name="NAME")
	private String name;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="VAX_TYPE")
	private String vaxType;
	
	@Column(name="PREVIOUS_COVID_RECORD")
	private int previousCovidRecord;
	
	@JoinColumn(name = "UIDAI")
	private int uidai;

	
	@Column(name="FIRST_VAX", table="VAX_DETAILS")
	private int firstVax;
	
	@Column(name="FIRST_VAX_DATE", table="VAX_DETAILS")
	private Date firstVaxDate;
	
	@Column(name="SECOND_VAX", table="VAX_DETAILS")
	private int secondVax;
	
	@Column(name="SECOND_VAX_DATE", table="VAX_DETAILS")
	private Date secondVaxDate;

	@Column(name="NO_OF_DOSE", table="VAX_DETAILS")
	private int noOfDose;
	
	@Column(name="BOOSTER_DOSE", table="VAX_DETAILS")
	private int boosterDose;
	
	@Column(name="BOOSTER_DOSE_DATE", table="VAX_DETAILS")
	private Date boosterDoseDate;

	
	public Vaccination() {
	}


	public Vaccination(int healthId, String name, int age, String gender, String vaxType, int previousCovidRecord,
			int uidai, int firstVax, Date firstVaxDate, int secondVax, Date secondVaxDate, int noOfDose,
			int boosterDose, Date boosterDoseDate) {
		super();
		this.healthId = healthId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.vaxType = vaxType;
		this.previousCovidRecord = previousCovidRecord;
		this.uidai = uidai;
		this.firstVax = firstVax;
		this.firstVaxDate = firstVaxDate;
		this.secondVax = secondVax;
		this.secondVaxDate = secondVaxDate;
		this.noOfDose = noOfDose;
		this.boosterDose = boosterDose;
		this.boosterDoseDate = boosterDoseDate;
	}


	public int getHealthId() {
		return healthId;
	}


	public void setHealthId(int healthId) {
		this.healthId = healthId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getVaxType() {
		return vaxType;
	}


	public void setVaxType(String vaxType) {
		this.vaxType = vaxType;
	}


	public int getPreviousCovidRecord() {
		return previousCovidRecord;
	}


	public void setPreviousCovidRecord(int previousCovidRecord) {
		this.previousCovidRecord = previousCovidRecord;
	}


	public int getUidai() {
		return uidai;
	}


	public void setUidai(int uidai) {
		this.uidai = uidai;
	}


	public int getFirstVax() {
		return firstVax;
	}


	public void setFirstVax(int firstVax) {
		this.firstVax = firstVax;
	}


	public Date getFirstVaxDate() {
		return firstVaxDate;
	}


	public void setFirstVaxDate(Date firstVaxDate) {
		this.firstVaxDate = firstVaxDate;
	}


	public int getSecondVax() {
		return secondVax;
	}


	public void setSecondVax(int secondVax) {
		this.secondVax = secondVax;
	}


	public Date getSecondVaxDate() {
		return secondVaxDate;
	}


	public void setSecondVaxDate(Date secondVaxDate) {
		this.secondVaxDate = secondVaxDate;
	}


	public int getNoOfDose() {
		return noOfDose;
	}


	public void setNoOfDose(int noOfDose) {
		this.noOfDose = noOfDose;
	}


	public int getBoosterDose() {
		return boosterDose;
	}


	public void setBoosterDose(int boosterDose) {
		this.boosterDose = boosterDose;
	}


	public Date getBoosterDoseDate() {
		return boosterDoseDate;
	}


	public void setBoosterDoseDate(Date boosterDoseDate) {
		this.boosterDoseDate = boosterDoseDate;
	}
	
}
