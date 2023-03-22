import openai

openai.api_key = "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"

messages = []
# system_msg = input("What type of chatbot would you like to create?\n")
messages.append({"role": "system", "content": "health issues"})

print("Hi, I am DigiAssist! Please let me know what symptoms you are facing: ")
while input != "quit()":
    message = input()
    messages.append({"role": "user", "content": message})
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=messages)
    reply = response["choices"][0]["message"]["content"]
    messages.append({"role": "assistant", "content": reply})
    print("\n" + reply + "\n")