% Resume Class File
% Standard FAANGPath / Trey Hunner resume class with compact 1-page spacing
\ProvidesClass{resume}[2010/07/10 v0.9 Resume class]

\LoadClass[10pt,letterpaper]{article}

\usepackage[parfill]{parskip}
\usepackage{array}
\usepackage{ifthen}

\nofiles
\pagestyle{empty}

% HEADINGS
\def \name#1{\def\@name{#1}}
\def \@name {}

\def \addressSep {$\diamond$}

\let \@addressone \relax
\let \@addresstwo \relax
\let \@addressthree \relax

\def \address #1{
  \@ifundefined{@addressone}{
    \def \@addressone {#1}
  }{
    \@ifundefined{@addresstwo}{
      \def \@addresstwo {#1}
    }{
      \def \@addressthree {#1}
    }
  }
}

\def \printname {
  \begingroup
    \hfil{\MakeUppercase{\namesize\bf \@name}}\hfil
    \nameskip\break
  \endgroup
}

\def \printaddress #1{
  \begingroup
    \def \\ {\addressSep\ }
    \centerline{#1}
  \endgroup
  \par
  \addressskip
}

\let\ori@document=\document
\renewcommand{\document}{
  \ori@document
  \printname
  \@ifundefined{@addressone}{}{
    \printaddress{\@addressone}
  }
  \@ifundefined{@addresstwo}{}{
    \printaddress{\@addresstwo}
  }
  \@ifundefined{@addressthree}{}{
    \printaddress{\@addressthree}
  }
}

% FORMATTING PARAMETERS - Tuned for exact 1-page fit
\def \namesize {\LARGE}
\def \nameskip {\vspace{-0.15em}}
\def \addressskip {\vspace{-0.3em}}
\def \sectionskip {\vspace{0.35em}}
\def \sectionlineskip {\vspace{0.15em}}

\newenvironment{rSection}[1]{
  \sectionskip
  \MakeUppercase{\bf #1}
  \sectionlineskip
  \hrule
  \vspace{0.25em}
  \begin{list}{}{
    \setlength{\leftmargin}{0em}
    \setlength{\topsep}{0pt}
    \setlength{\partopsep}{0pt}
    \setlength{\parsep}{0pt}
    \setlength{\itemsep}{0pt}
  }
  \item[]
}{
  \end{list}
  \vspace{-0.2em}
}

\newenvironment{rSubsection}[4]{
  {\bf #1} \hfill {#2}
  \ifthenelse{\equal{#3}{}}{}{
    \\
    {\em #3} \hfill {\em #4}
  }
  \smallskip
  \begin{list}{$\cdot$}{\leftmargin=0em}
   \itemsep -0.5em \vspace{-0.5em}
}{
  \end{list}
  \vspace{0.3em}
}
