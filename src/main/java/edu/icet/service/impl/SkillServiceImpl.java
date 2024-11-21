package edu.icet.service.impl;

import edu.icet.dto.Skill;
import edu.icet.entity.SkillEntity;
import edu.icet.repository.SkillRepository;
import edu.icet.service.SkillService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createSkill(Skill skill) {
        SkillEntity skillEntity = modelMapper.map(skill, SkillEntity.class);
        SkillEntity savedSkill = skillRepository.save(skillEntity);
        modelMapper.map(savedSkill, Skill.class);
    }

    @Override
    public void updateSkill(Long skillId, Skill skill) {
        SkillEntity existingSkill = skillRepository.findById(skillId)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found"));

        existingSkill.setSkillName(skill.getSkillName());

        SkillEntity updatedSkill = skillRepository.save(existingSkill);
        modelMapper.map(updatedSkill, Skill.class);
    }

    @Override
    public Optional<Skill> getSkillById(Long skillId) {
        return skillRepository.findById(skillId)
                .map(skillEntity -> modelMapper.map(skillEntity, Skill.class));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillEntity -> modelMapper.map(skillEntity, Skill.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkill(Long skillId) {
        if (!skillRepository.existsById(skillId)) {
            throw new EntityNotFoundException("Skill not found");
        }
        skillRepository.deleteById(skillId);
    }
}
