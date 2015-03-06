INSERT INTO template_zones (zone_id, template_id)
SELECT template.zone_id,  template.id
FROM template WHERE template.zone_id is not null;