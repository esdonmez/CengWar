
public class Computer 
{
	private String Name;
	private String Color;
	private String Mode;
	private int Life;
	private int AttackDamage;
	private int AttackSpeed;
	private int MovementSpeed;
	private int ViewRange;
	private int Needs;
	private int CreatingTime;
	private OldStack Bag;
	private int Level;
	private static int Id = 1;
	
	public Computer()
	{
		this.Name = "CengMAN0" + Id;
		this.Color = "RED";
		this.Mode = null;
		this.Life = 100;
		this.Level = 1;
		this.AttackDamage = 10 * Level;
		this.AttackSpeed = 1;
		this.MovementSpeed = 1;
		this.ViewRange = 5;
		this.Needs = 50;
		this.CreatingTime = 8;
		this.Bag = new OldStack();
	}
}
