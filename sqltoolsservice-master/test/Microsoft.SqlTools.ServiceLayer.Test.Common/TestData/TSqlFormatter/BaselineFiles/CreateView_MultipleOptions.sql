CREATE VIEW my_view_name
WITH
    SCHEMABINDING,
    ENCRYPTION,
    VIEW_METADATA
AS
    SELECT *
    FROM mytable
