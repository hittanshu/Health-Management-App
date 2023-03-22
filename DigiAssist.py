import tkinter as tk
import openai

openai.api_key = "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"

messages = []
messages.append({"role": "system", "content": "health issues"})

def send_message(event=None):
    message = input_field.get()
    messages.append({"role": "user", "content": message})
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=messages)
    reply = response["choices"][0]["message"]["content"]
    messages.append({"role": "assistant", "content": reply})
    chat_log.insert(tk.END, "You: " + message + "\n")
    chat_log.insert(tk.END, "DigiAssist: " + reply + "\n\n")
    input_field.delete(0, tk.END)

def quit_chat(event=None):
    root.destroy()

root = tk.Tk()
root.title("DigiAssist")
root.geometry("600x600")

chat_log = tk.Text(root, height=30, width=70)
chat_log.pack(padx=10, pady=10)
chat_log.insert(tk.END, "DigiAssist: " + "Hi, I am DigiAssist! Your one-stop health assistant! Please let me know what symptoms you are facing: " + "\n")

input_field = tk.Entry(root, width=70)
input_field.pack(side=tk.LEFT, padx=10, pady=10)

send_button = tk.Button(root, text="Send", command=send_message)
send_button.pack(side=tk.LEFT, padx=10, pady=10)

quit_button = tk.Button(root, text="Quit", command=quit_chat)
quit_button.pack(side=tk.RIGHT, padx=10, pady=10)

input_field.bind("<Return>", send_message)

root.mainloop()
