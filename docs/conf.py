#  Copyright (C) 2020  Consiglio Nazionale delle Ricerche
#
#      This program is free software: you can redistribute it and/or modify
#      it under the terms of the GNU Affero General Public License as
#      published by the Free Software Foundation, either version 3 of the
#      License, or (at your option) any later version.
#
#      This program is distributed in the hope that it will be useful,
#      but WITHOUT ANY WARRANTY; without even the implied warranty of
#      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#      GNU Affero General Public License for more details.
#
#      You should have received a copy of the GNU Affero General Public License
#      along with this program.  If not, see <https://www.gnu.org/licenses/>.

import sphinx_material

# Register the theme as an extension to generate a sitemap.xml
# extensions.append('sphinx_material')
from recommonmark.parser import CommonMarkParser

source_parsers = {
    '.md': CommonMarkParser,
}
project = 'Sistema Informativo Gestione Linee di Attività'
release = '0.1'
author = u'Consiglio Nazionale delle Ricerche'

show_authors = True
# Grouping the document tree into LaTeX files. List of tuples
# (source start file, target name, title, author, documentclass [howto/manual]).
latex_documents = [
    ('index', 'Manuale-SIGLA.tex', project, author, 'manual'),
]
latex_elements = {
    'extraclassoptions': 'openany,oneside'
}
epub_basename = u'Manuale-SIGLA'
# Choose the material theme
html_theme = 'sphinx_material'
#html_theme = 'sphinx_materialdesign_theme'
# Get the them path
html_theme_path = sphinx_material.html_theme_path()
# Register the required helpers for the html context
html_context = sphinx_material.get_html_context()
copyright = "2004 - 2020 Consiglio Nazionale delle Ricerche"
html_show_sourcelink = False
html_favicon = "favicon.ico"
html_logo = "logo.png"
latex_logo = 'logo.png'
html_title = "Home"
language = "it"
# The master toctree document.
master_doc = 'index'
source_suffix = ['.rst','.md']
html_sidebars = {
    '**': ['localtoc.html', 'globaltoc.html', 'sourcelink.html', 'searchbox.html']
}
# These folders are copied to the documentation's HTML output
html_static_path = ['_static']
templates_path = ['_templates']
# These paths are either relative to html_static_path
# or fully qualified paths (eg. https://...)
html_css_files = [
    'css/extra.css',
    'css/hacks.css',
    'css/material.css'
]
extensions = [
    "sphinx.ext.autodoc",
    "numpydoc",
    "sphinx.ext.doctest",
    "sphinx.ext.extlinks",
    "sphinx.ext.intersphinx",
    "sphinx.ext.todo",
    "sphinx.ext.mathjax",
    "sphinx.ext.viewcode",
    "nbsphinx",
    "sphinx_markdown_tables",
    'sphinx.ext.githubpages'
]
html_theme_options = {
    'base_url': 'https://consiglionazionaledellericerche.github.io/sigla-main',
    'repo_url': 'https://github.com/consiglionazionaledellericerche/sigla-main/',
    'repo_name': 'consiglionazionaledellericerche/sigla-main',
    'nav_title': 'Sistema Informativo Gestione Linee di Attività',
    'html_minify': True,
    'css_minify': True,
    'version_dropdown': False,
    'globaltoc_depth': 5,
    # Set the color and the accent color
    'color_primary': 'indingo',
    'color_accent': 'light-blue',
    'nav_links': [
        {'href': 'CHANGELOG', 'internal': True, 'title': 'Changelog'},
        {
            'href': 'https://contab.cnr.it/SIGLANG',
            'internal': False,
            'title': 'SIGLA <i class="md-icon">launch</i>'
        }
    ]
}
