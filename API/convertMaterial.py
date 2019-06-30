inputString = input("Give the string please\n\t")

outputString = ""

for subs in inputString.split("\n"):
    if "(" in subs:
        ind = subs.find("(")
        material = subs[0:ind]
        outputString += subs[0:ind] + ",\n"
    else:
        outputString += subs + "\n"

with open("output.txt", "w") as file:
    file.write(outputString)
