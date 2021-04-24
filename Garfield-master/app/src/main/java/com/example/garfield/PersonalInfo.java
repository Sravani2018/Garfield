/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may Not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.garfield;

import java.io.Serializable;

public class PersonalInfo implements Serializable {

    private String name;
    private String emergencyContact;
    private String emergencyContactNo;
    private String doctor;
    private String doctorNo;
    private String personalNo;
    private String address;

    public PersonalInfo() {
    }

    public PersonalInfo(String name, String emergencyContact,String emergencyContactNo,String personalNo,String address,String doctor,String doctorNo) {
        this.name = name;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.emergencyContactNo = emergencyContactNo;
        this.doctor = doctor;
        this.doctorNo = doctorNo;
        this.personalNo = personalNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setDoctor(String doctor) {
        this.doctor= doctor;
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(String personalNo) {
        this.personalNo = personalNo;
    }


}
