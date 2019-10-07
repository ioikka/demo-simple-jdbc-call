CREATE PROCEDURE test_ret_val (
	@input1 int,
	@output1 int output)
AS
BEGIN
    DECLARE @ReturnCode INT;
	set @output1 = 11111;
	set @ReturnCode = 22000;
	return @ReturnCode;
END
