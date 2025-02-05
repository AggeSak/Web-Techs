from flask import Flask, jsonify, request

app = Flask(__name__)

@app.route('/')
def home():
    return "Welcome to the DnD Web Backend!"

@app.route('/api/data', methods=['GET'])
def get_data():
    data = {
        'name': 'DnD Character',
        'class': 'Wizard',
        'level': 5
    }
    return jsonify(data)

@app.route('/api/data', methods=['POST'])
def post_data():
    new_data = request.json
    return jsonify(new_data), 201

if __name__ == '__main__':
    app.run(debug=True)