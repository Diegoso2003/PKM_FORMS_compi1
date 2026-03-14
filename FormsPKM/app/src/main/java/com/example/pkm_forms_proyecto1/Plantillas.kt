package com.example.pkm_forms_proyecto1

const val SECTION = """
SECTION [
    width: 30,
    height: 30,
    pointX: 5,
    pointY: 5,
    elements {
        
    }
]
"""
const val TEXT = """
        TEXT [
            content: "texto"
        ]
"""
const val OPEN_QUESTION = """
        OPEN_QUESTION [
            label: "pregunta abierta"
        ]
"""
const val DROP_QUESTION = """
        DROP_QUESTION [
            label: "pregunta drop",
            options: {"opcion 1", "opcion 2", "opcion 3"},
            correct: 0
        ]
"""
const val SELECT_QUESTION = """
        SELECT_QUESTION [
            label: "pregunta drop",
            options: {"opcion 1", "opcion 2", "opcion 3"},
            correct: 0
        ]
"""
const val MULTIPLE_QUESTION = """
        MULTIPLE_QUESTION [
            options: {"opcion 1", "opcion 2", "opcion 3"},
            correct: {0, 1}
        ]
"""
const val ESTILO = """
STYLES [
    "color": #000000,
    "background color": #FFFFFF,
    "font family": MONO,
    "text size": 11
]
"""