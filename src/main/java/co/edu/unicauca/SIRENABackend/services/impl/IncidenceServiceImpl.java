package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;
import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceRepository;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceTypeRepository;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;

@Service
public class IncidenceServiceImpl implements IncidenceService {

    @Autowired
    private IIncidenceRepository incidenceRepository;
    @Autowired
    private IIncidenceTypeRepository incidenceTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<IncidenceRes> getIncidences() {

        ArrayList<IncidenceModel> incidencesFound = (ArrayList<IncidenceModel>) incidenceRepository.findAll();
        ArrayList<IncidenceRes> incidencesRes = new ArrayList<>();

        for (IncidenceModel incidence : incidencesFound) {
            incidencesRes.add(IncidenceRes.builder()
                    .name(incidence.getName())
                    .teacherName(incidence.getTeacherName().getUsername())
                    .incidenceType(incidence.getInsidenciaType())
                    .build());
        }

        return incidencesRes;
    }

    @Override
    @Transactional
    public IncidenceRes saveIncidence(IncidenceReq prmIncidence) {
        IncidenceTypeModel incidenceFound = incidenceTypeRepository
                .findByName(prmIncidence.getIncidenceType());
        if (incidenceFound == null) {
            return null;
        }

        IncidenceModel incidence = IncidenceModel.builder()
                .id(prmIncidence.getId())
                .name(prmIncidence.getName())
                .teacherName(prmIncidence.getTeacher())
                .insidenciaType(incidenceFound)
                .build();
        IncidenceModel incidenceSave = incidenceRepository.save(incidence);

        IncidenceRes incidenceRes = IncidenceRes.builder()
                .name(incidenceSave.getName())
                .teacherName(incidenceSave.getTeacherName().getUsername())
                .incidenceType(incidenceSave.getInsidenciaType())
                .build();

        return incidenceRes;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidenceRes> getIncidenceById(Integer prmId) {
        Optional<IncidenceModel> incidenceFound = incidenceRepository.findById(prmId);

        IncidenceRes incidenceRes = incidenceFound.map(incidence -> IncidenceRes.builder()
                .name(incidence.getName())
                .teacherName(incidence.getTeacherName().getUsername())
                .incidenceType(incidence.getInsidenciaType())
                .build()).orElse(null);

        return incidenceRes != null ? Optional.of(incidenceRes) : Optional.empty();
    }

    @Transactional
    public boolean deleteIncidenceById(Integer prmId) {
        try {
            incidenceRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
