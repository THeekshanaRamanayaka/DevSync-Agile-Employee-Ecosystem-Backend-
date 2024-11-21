package edu.icet.service.impl;

import edu.icet.dto.SkillAssign;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.SkillAssignEntity;
import edu.icet.entity.SkillEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.repository.SkillAssignRepository;
import edu.icet.repository.SkillRepository;
import edu.icet.service.SkillAssignService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillAssignServiceImpl implements SkillAssignService {
    private final SkillAssignRepository skillAssignRepository;
    private final SkillRepository skillRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public SkillAssign assignSkillToEmployee(SkillAssign skillAssign) {
        SkillEntity skill = skillRepository.findById(Long.parseLong(skillAssign.getSkillId()))
                .orElseThrow(() -> new EntityNotFoundException("Skill not found"));

        EmployeeEntity employee = employeeRepository.findById(skillAssign.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        SkillAssignEntity skillAssignEntity = modelMapper.map(skillAssign, SkillAssignEntity.class);
        skillAssignEntity.setSkill(skill);
        skillAssignEntity.setEmployee(employee);

        SkillAssignEntity savedSkillAssign = skillAssignRepository.save(skillAssignEntity);
        return modelMapper.map(savedSkillAssign, SkillAssign.class);
    }

    @Override
    public SkillAssign updateSkillAssignment(Long skillAssignId, SkillAssign skillAssign) {
        SkillAssignEntity existingSkillAssign = skillAssignRepository.findById(skillAssignId)
                .orElseThrow(() -> new EntityNotFoundException("Skill Assignment not found"));

        existingSkillAssign.setProficiency(skillAssign.getProficiency());
        existingSkillAssign.setYearsOfExperience(skillAssign.getYearsOfExperience());

        if (!existingSkillAssign.getSkill().getSkillId().equals(Long.parseLong(skillAssign.getSkillId()))) {
            SkillEntity skill = skillRepository.findById(Long.parseLong(skillAssign.getSkillId()))
                    .orElseThrow(() -> new EntityNotFoundException("Skill not found"));
            existingSkillAssign.setSkill(skill);
        }

        if (!existingSkillAssign.getEmployee().getEmployeeId().equals(skillAssign.getEmployeeId())) {
            EmployeeEntity employee = employeeRepository.findById(skillAssign.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            existingSkillAssign.setEmployee(employee);
        }

        SkillAssignEntity updatedSkillAssign = skillAssignRepository.save(existingSkillAssign);
        return modelMapper.map(updatedSkillAssign, SkillAssign.class);
    }

    @Override
    public Optional<SkillAssign> getSkillAssignmentById(Long skillAssignId) {
        return skillAssignRepository.findById(skillAssignId)
                .map(skillAssignEntity -> modelMapper.map(skillAssignEntity, SkillAssign.class));
    }

    @Override
    public List<SkillAssign> getAllSkillAssignments() {
        return skillAssignRepository.findAll().stream()
                .map(skillAssignEntity -> modelMapper.map(skillAssignEntity, SkillAssign.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillAssign> getSkillAssignmentsByEmployeeId(Long employeeId) {
        return skillAssignRepository.findByEmployee_EmployeeId(employeeId).stream()
                .map(skillAssignEntity -> modelMapper.map(skillAssignEntity, SkillAssign.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkillAssignment(Long skillAssignId) {
        if (!skillAssignRepository.existsById(skillAssignId)) {
            throw new EntityNotFoundException("Skill Assignment not found");
        }
        skillAssignRepository.deleteById(skillAssignId);
    }
}
