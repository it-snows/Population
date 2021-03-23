CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetCitiesInRegion`(
IN reg_id int
)
BEGIN
	SELECT * FROM v_city_full_data WHERE region_id = reg_id;
END