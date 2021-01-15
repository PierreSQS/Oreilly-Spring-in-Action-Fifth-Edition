delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;

insert into Ingredient (id, name, type)
            values ('FLTO', 'Flour Tortilla', 1);
insert into Ingredient (id, name, type)
            values ('COTO', 'Corn Tortilla', 1);
insert into Ingredient (id, name, type)
            values ('GRBF', 'Ground Beef', 2);
insert into Ingredient (id, name, type)
            values ('CARN', 'Carnitas', 2);
insert into Ingredient (id, name, type)
            values ('TMTO', 'Diced Tomatoes', 3);
insert into Ingredient (id, name, type)
            values ('LETC', 'Lettuce', 3);
insert into Ingredient (id, name, type)
            values ('CHED', 'Cheddar', 4);
insert into Ingredient (id, name, type)
            values ('JACK', 'Monterrey Jack', 4);
insert into Ingredient (id, name, type)
            values ('SLSA', 'Salsa', 5);
insert into Ingredient (id, name, type)
            values ('SRCR', 'Sour Cream', 5);