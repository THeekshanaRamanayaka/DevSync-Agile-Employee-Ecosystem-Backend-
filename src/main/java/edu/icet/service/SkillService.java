package edu.icet.service;

import edu.icet.dto.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Skill createSkill(Skill skill);
    Skill updateSkill(Long skillId, Skill skill);
    Optional<Skill> getSkillById(Long skillId);
    List<Skill> getAllSkills();
    void deleteSkill(Long skillId);
}
