package com.medicalrecord.web.adapter;

import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.DoctorDto;
import com.medicalrecord.web.view.model.DoctorModelView;
import com.medicalrecord.web.view.model.DoctorModelViewData;
import com.medicalrecord.web.view.model.DoctorModelViewDataLight;
import com.medicalrecord.web.view.model.SearchDoctorModelViewData;
import org.springframework.stereotype.Component;

@Component
public class DoctorModelViewAdapter {

    public DoctorModelView convertDoctorDtoToModelView(DoctorDto doctorDto) {

        DoctorModelView doctorModelView = new DoctorModelView();
        DoctorModelViewData doctorModelViewData = new DoctorModelViewData();

        doctorModelViewData.setAge(doctorDto.getDoctorData().getAge());
        doctorModelViewData.setEgn(doctorDto.getDoctorData().getEgn());
        doctorModelViewData.setFirstName(doctorDto.getDoctorData().getFirstName());
        doctorModelViewData.setLastName(doctorDto.getDoctorData().getLastName());
        doctorModelViewData.setSpecialties(doctorDto.getDoctorData().getSpecialties());
        doctorModelViewData.setWorkingPlace(doctorDto.getDoctorData().getWorkingPlace());

        doctorModelView.setId(doctorDto.getId());
        doctorModelView.setDoctorData(doctorModelViewData);

        return doctorModelView;

    }

    public DoctorData convertDoctorModelViewDataToDoctorData(DoctorModelViewData doctorModelViewData) {

        DoctorData doctorData = new DoctorData();

        doctorData.setAge(doctorModelViewData.getAge());
        doctorData.setEgn(doctorModelViewData.getEgn());
        doctorData.setFirstName(doctorModelViewData.getFirstName());
        doctorData.setLastName(doctorModelViewData.getLastName());
        doctorData.setSpecialties(doctorModelViewData.getSpecialties());
        doctorData.setWorkingPlace(doctorModelViewData.getWorkingPlace());

        return doctorData;

    }

    public DoctorData convertSearchDoctorModelViewDataToDoctorData(SearchDoctorModelViewData doctorModelViewData) {

        DoctorData doctorData = new DoctorData();

        doctorData.setFirstName(doctorModelViewData.getFirstName());
        doctorData.setLastName(doctorModelViewData.getLastName());
        doctorData.setWorkingPlace(doctorModelViewData.getWorkingPlace());

        return doctorData;

    }

}
