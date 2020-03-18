INSERT INTO defaultvalues(activity_type, co2, water, land)
VALUES('f_vegan', 0.580, 177.82, 0.780),
('f_vegetarian', 0.810, 421.87, 0.630),
('f_pescetarian', 1.790, 91.27, 0.670),
('f_meat', 2.570, 729.20, 2.290),
('t_car', 0.119, 0, 0),
('t_electric', 0.038, 0, 0),
('t_hybrid', 0.089, 0, 0),
('t_bus', 0.060, 0, 0),
('t_train', 0.028, 0, 0),
('t_plane', 0.070, 0, 0),
('t_bike', 0, 0, 0),
('h_gas', 22, 0, 0),
('h_electricity', 28.01, 0, 0),
('s_solar', 0.649, 0, 0),
('s_solar_energy', 234, 0, 0);

INSERT INTO badges(badge_id, badge_image, badge_name)
VALUES(1,'tesla.png', 'Bought an electric car'),
(2,'solar1.png', 'Installed 5 solar panels'),
(3,'solar2.png', 'Installed 10 solar panels'),
(4,'solar3.png', 'Installed 20 solar panels'),
(5,'tree1.png', 'Planted a tree'),
(6,'tree2.png', 'Planted 10 trees'),
(7,'tree3.png', 'Planted 30 trees');


INSERT INTO Users(user_Id, full_name, user_name, photo, co2_day, co2_week,
eco_footprint, km_cycle, km_public_transport, km_walk, land_day, land_month,
land_week, level, num_local_meals, num_pesc, num_solar_panels, num_trees_planted, num_veg, num_vegan, co2_all, land_all, total_points, water_all, water_day, water_month, water_week, co2_month)
VALUES ('1', 'testuser', 'testusername', 'testphoto', 0, 0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0);