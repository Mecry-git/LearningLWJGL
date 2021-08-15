#version 330 core

in vec3 pos;
in vec3 col;
in vec2 tex;

out vec3 passCol;
out vec2 passTex;

uniform mat4 model;
uniform mat4 prjtn;

void main() {
    gl_Position = prjtn * model * vec4(pos, 1);
    passCol = col;
    passTex = tex;
}
