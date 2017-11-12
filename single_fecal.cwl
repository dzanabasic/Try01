cwlVersion: v1.0
class: Workflow
inputs:
  dir:
    type: string
    inputBinding:
      position: 1
outputs: []

steps:
  single_gz_to_otu:
    run: single_gz_to_otu.cwl
    in:
      dir: dir
    out:
      [otudone]

  single_otu_to_figs:
    run: single_otu_to_figs.cwl
    in:
      dir: dir
      figs:
        source: "#single_gz_to_otu/otudone"
    out:
      [figsdone]
