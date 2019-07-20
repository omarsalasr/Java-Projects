JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Character.java \
	Player.java \
	NPC.java \
	DecisionMaker.java \
	UI.java \
	AI.java \
	AIAlly.java \
	AIEnemy.java \
	AIPlayful.java \
	Ally.java \
	Enemy.java \
	KeyboardScanner.java \
	Move.java \
	MoveExit.java \
	MoveAttack.java \
	MoveDirection.java \
	MoveDrop.java \
	MoveGet.java \
	MoveInventory.java \
	MoveLook.java \
	MoveSteal.java \
	MoveUse.java \
	MoveEquip.java \
	Direction.java \
	Place.java \
	DarkRoom.java \
	BossRoom.java \
	HealRoom.java \
	Game.java \
	Artifact.java \
	wand.java \
	weapons.java \
	potions.java \
	Playful.java \
	GameTester.java \
	IO.java \
	UserInterface.java \
	TextInterface.java \
	GUIOmar.java \
	GUIDom.java \
	GUIRahul.java

MAIN = GameTester

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
