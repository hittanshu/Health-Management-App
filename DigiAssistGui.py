import tkinter as tk
import openai

openai.api_key = "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"

def send_message():
    message = user_input.get()
    messages.insert(tk.END, "You: " + message + "\n")
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        prompt=message,
        temperature=0.5,
        max_tokens=1024,
        top_p=1,
        frequency_penalty=0,
        presence_penalty=0,
        stop=["\n"]
    )
    reply = response.choices[0].text.strip()
    messages.insert(tk.END, "DigiAssist: " + reply + "\n")
    user_input.delete(0, tk.END)

root = tk.Tk()
root.title("DigiAssist")

# Chat display
messages = tk.Text(root, width=60, height=20)
messages.config(state=tk.DISABLED)
messages.grid(row=0, column=0, padx=10, pady=10)

# User input
user_input = tk.Entry(root, width=60)
user_input.grid(row=1, column=0, padx=10, pady=10)

# Send button
send_button = tk.Button(root, text="Send", command=send_message)
send_button.grid(row=1, column=1, padx=10, pady=10)

root.mainloop()